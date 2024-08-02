package choi.choice.service;

import choi.choice.framework.cloud.CloudFileSystemInterface;
import choi.choice.framework.commons.ConfigService;
import choi.choice.framework.commons.StringService;
import choi.choice.framework.exception.NotSupportedUploadFileException;
import choi.choice.framework.utils.SecureCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;

@Slf4j
public class IOService {

	@Autowired
	private CloudFileSystemInterface cloudFileSystem;

	@Autowired
	private ConfigService configService;

    /**
     * The constant UNIX_SEPARATOR.
     */
    static final char UNIX_SEPARATOR = '/';

    /**
     * The constant WINDOWS_SEPARATOR.
     */
    static final char WINDOWS_SEPARATOR = '\\';

    /**
     * 디렉토리 및 하위 디렉토리의 파일을 전체 삭제한다.
     * <p/>
     * <p/>
     * <p/>
     *
     * <pre>
     * FileUtil.deleteDirectory(new File(&quot;&quot;));
     * </pre>
     *
     * @param file 삭제 디렉토리
     * @since 2015. 3. 3
     */
// 	fileupload 에서 사용하는 것들은 AWSS3에서 일단 보류
	public static void deleteAllDirectory(final File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();

			for (File nextFile : files) {
				deleteAllDirectory(nextFile);
			}
		}

		file.delete();
	}

    /**
     * 파일 확장자를 리턴 한다.
     * <p/>
     * <p/>
     * <p/>
     * getIoService().getExtension("foo.txt") --> "txt" <br/>
     * getIoService().getExtension("a/b/c.jpg") --> "jpg"<br/>
     * getIoService().getExtension("a/b.txt/c") --> ""<br/>
     * getIoService().getExtension("a/b/c") --> ""<br/>
     *
     * @param filename 파일명
     * @return String 확장자 @ the exception
     * @since 2015. 3. 3
     */
    public static String getExtension(String filename) {
		return FilenameUtils.getExtension(filename);

	}

    /**
     * 파일 확장자를 삭제 한다.
     * <p/>
     * <p/>
     * <p/>
     * getIoService().removeExtension("foo.txt") --> "foo"<br/>
     * getIoService().removeExtension("a/b/c.jpg") --> "a/b/c"<br/>
     * getIoService().removeExtension("a/b/c") --> "a/b/c"<br/>
     * getIoService().removeExtension("a.b/c") --> "a.b/c"<br/>
     *
     * @param filename [설명]
     * @return String [설명] @ the exception
     * @since 2015. 3. 3
     */
    public static String removeExtension(String filename) {
		return FilenameUtils.removeExtension(filename);
	}

    /**
     * 확장자 유무 체크를 한다.
     * <p/>
     * <p/>
     * getIoService().isExtension("a.txt", "txt") = "true"
     *
     * @param filename  파일 명
     * @param extension 체크 확장자
     * @return true :존재 할 경우, false:존재하지 않을 경우 @ the exception
     * @since 2015. 3. 3
     */
    public static boolean isExtension(String filename, String extension) {
		return FilenameUtils.isExtension(filename, extension);
	}

    /**
     * 확장자 유무 체크를 한다.
     * <p/>
     * <p/>
     * <p/>
     * getIoService().isExtension("a.txt", {"txt","jpg"}) = "true"
     *
     * @param filename  파일 명
     * @param extension 체크 확장자 배열
     * @return true :존재 할 경우, false:존재하지 않을 경우 @ the exception
     * @since 2015. 3. 3
     */
    public static boolean isExtension(String filename, String[] extension) {
		return FilenameUtils.isExtension(filename, extension);
	}

    /**
     * 확장자를 포함한 파일명을 리턴 한다.
     * <p/>
     * <p/>
     * <p/>
     * getIoService().getName("a/b/c.txt") = "c.txt"
     * getIoService().getName("a/b/c") = "c" getIoService().getName("a/b/c/") =
     * ""
     *
     * @param fileName 전체 full 경로 파일 명
     * @return String 파일 명 @ the exception
     * @since 2015. 3. 3
     */
    public static String getName(String fileName) {
		return FilenameUtils.getName(fileName);
	}

    /**
     * 확장자를 제외한 파일명을 린턴 한다.
     * <p/>
     * <p/>
     * <p/>
     * getIoService().getName("a/b/c.txt") = "c" getIoService().getName("a.txt")
     * = "a" getIoService().getName("a/b/c/") = ""
     *
     * @param fileName 전체 full 경로 파일 명
     * @return String 파일 명 @ the exception
     * @since 2015. 3. 3
     */
    public static String getBaseName(String fileName) {
		return FilenameUtils.getBaseName(fileName);
	}

    /**
     * Move file to file.
     *
     * @param srcFile  the src file
     * @param destFile the dest file
     */
    public static void moveFileToFile(File srcFile, File destFile) {
		try {
			File path = destFile.getParentFile();
			log.debug("Path : {}, {}", path.getPath(), path.exists());
			if (!path.exists()) {
				try {
					log.debug("MKDIR : {}", path.mkdirs());
				} catch (Exception e) {
					log.warn(e.getMessage());
				}
				// setPermission(path);
			}

			FileUtils.moveFile(srcFile, destFile);
			setPermission(destFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

    /**
     * Sets permission.
     *
     * @param destFile the dest file
     */
    public static void setPermission(File destFile) {
		String os = System.getProperty("os.name").toLowerCase();

		if (os.indexOf("win") < 0) {
			log.debug("[os=" + os + "] PosixFilePermission setPermission");

			Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
			// add owners permission
			perms.add(PosixFilePermission.OWNER_READ);
			perms.add(PosixFilePermission.OWNER_WRITE);
			perms.add(PosixFilePermission.OWNER_EXECUTE);
			// add group permissions
			perms.add(PosixFilePermission.GROUP_READ);
			perms.add(PosixFilePermission.GROUP_WRITE);
			perms.add(PosixFilePermission.GROUP_EXECUTE);
			// add others permissions
			perms.add(PosixFilePermission.OTHERS_READ);
			perms.add(PosixFilePermission.OTHERS_WRITE);
			perms.add(PosixFilePermission.OTHERS_EXECUTE);

			/**
			 * fortify 오탐 - 외부입력에 의한 경로가 없음
			 */
			Path parent = Paths.get(destFile.getPath());
			while (parent != null) {
				try {
					Files.setPosixFilePermissions(parent, perms);
					parent = parent.getParent();
				} catch (Exception fe) {
					log.warn(fe.getMessage());
					break;
				}
			}
		}
	}

    /**
     * 업로드 파일을 지정된 위치에 저장 한다.
     * <p/>
     * <p/>
     *
     * @param multipartFile [설명]
     * @param path          [설명]
     * @param fileName      [설명] @ the exception
     * @return the string
     * @since 2015. 3. 24
     */
    public static String saveUploadFile(MultipartFile multipartFile, String path, String fileName) {

		try {

			log.debug("IOService.saveUploadFile start path : {} ",path);
			File pFile = new File(path);

			// 디렉토리가 존재 하지 않을 경우 생성
			if (!pFile.exists()) {

				log.debug("before.pFile.canExecute()===>" + pFile.canExecute());

				pFile.setExecutable(true, false);
				pFile.setReadable(true, false);
				pFile.mkdirs();
				// 폴더 생성시 권한 777 부여
				setPermission(pFile);
				log.debug("IOService.saveUploadFile setsetPermission ");
				log.debug("after.pFile.getPath()===>" + pFile.getPath());
				log.debug("after.pFile.canExecute()===>" + pFile.canExecute());
			}

	        /**
	         * fortify - 경로 조작 및 자원삽입
	         */
			log.debug("IOService.saveUploadFile SecureCode.relativePathCheck(path) path : {} "+path);
	        SecureCode.relativePathCheck(path);
			log.debug("IOService.saveUploadFile SecureCode.relativePathCheck(path) path : {} "+fileName);
	        SecureCode.fileNameCheck(fileName);

			String uploadFile = trimToPath(path) + fileName;
			log.debug("IOService.saveUploadFile " + uploadFile);

			File targetFile = new File(uploadFile);
			log.debug("IOService.saveUploadFile multipartFile.transferTo(targetFile) before.targetFile.canExecute()===>" + targetFile.canExecute());

			multipartFile.transferTo(targetFile);
			log.debug("IOService.saveUploadFile targetFile.setReadable");
			targetFile.setReadable(true, false);
			// 파일 777 권한 부여
			log.debug("IOService.saveUploadFile setPermission(targetFile);");
			setPermission(targetFile);

			log.debug("IOService.saveUploadFile after.trimToPath(path) + fileName===>" + uploadFile);
			log.debug("IOService.saveUploadFile end after.targetFile.canExecute()===>" + targetFile.canExecute());

			return uploadFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

    /**
     * 디렉토리 마지막에 구분자가 없을 경우 구분자를 추가해 준다..
     * <p/>
     * c:/aaa/bbb => c:/aaa/bbb/ c:/aaa/bbb/ => c:/aaa/bbb/
     * <p/>
     *
     * @param path [설명]
     * @return String [설명] @ the exception
     * @since 2015. 3. 24
     */
    public static String trimToPath(String path) {

		String convertPath = path;

		// 맨 위체 구분자가 없을 경우 추가 한다.
		if (!(StringUtils.endsWith(path, "" + UNIX_SEPARATOR) || StringUtils.endsWith(path, "" + WINDOWS_SEPARATOR))) {
			convertPath += "" + UNIX_SEPARATOR;
		}

		return convertPath;
	}

    /**
     * String [] excludeExtensions에 하나라도 포함이 되면 NotSupportedUploadFileException
     * 발생.
     * <p/>
     * <p/>
     * white list=====================
     *
     * @param files             [설명]
     * @param excludeExtensions [설명] @ the exception
     * @since 2015. 3. 24
     */
    public static void availableUploadExcludeExtension(List<MultipartFile> files, String[] excludeExtensions) {

		boolean available = false;
		String fileName = "";
		for (MultipartFile file : files) {
			String extension = getExtension(file.getOriginalFilename());

			available = true;

			for (String excludeExtension : excludeExtensions) {
				if (excludeExtension.equalsIgnoreCase(extension)) {
					available = false;
					break;
				}
			}

			if (!available) {
				fileName = file.getOriginalFilename();
				break;
			}
		}

		// 부접한한 확장자면 NotSupportedUploadFileException를 리턴 한다.
		if (!available) {
			log.debug("availableUploadExcludeExtension NotSupportedUploadFileException : {} , {}", excludeExtensions, fileName);
			throw new NotSupportedUploadFileException(null);
		}
	}

    /**
     * 허용가능 확장자를 체크하는
     *
     * @param files             파일들
     * @param includeExtensions 허용 확장자 문자열 @
     */
    public static void availableUploadIncludeExtension(List<MultipartFile> files, String[] includeExtensions) {

		boolean available = false;
		String fileName = "";
		for (MultipartFile file : files) {
			String extension = getExtension(file.getOriginalFilename());
			long fileSize = file.getSize();
			fileName = file.getOriginalFilename();

			if (fileSize > 0 && StringService.isNotEmpty(fileName)) {
				available = false;

				for (String includeExtension : includeExtensions) {
					if (includeExtension.equalsIgnoreCase(extension)) {
						available = true;
					}
				}
				if (!available) {
					fileName = file.getOriginalFilename();
					break;
				}
			} else {
				available = true;
			}
		}

		// 부접한한 확장자면 NotSupportedUploadFileException를 리턴 한다.
		if (!available) {
			log.debug("availableUploadIncludeExtension NotSupportedUploadFileException : {} , {}", includeExtensions, fileName);
			throw new NotSupportedUploadFileException(null);
		}
	}

    /**
     * Gets current user detail.
     *
     * @return the current user detail
     */
	public static Object getCurrentUserDetail() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null || context.getAuthentication() == null) {
			return null;
		} else {
			return context.getAuthentication().getPrincipal();
		}
	}

    /**
     * 프런트 (pc, mobile)에서 인증 여부를 판단 한다.
     * <p/>
     * <p/>
     *
     * @return true :[설명], false:[설명]
     * @since 2015. 4. 9
     */
    @SuppressWarnings("unchecked")
	public static boolean hasRole() {
		return hasRole(SecurityParam.DEFAULT_ROLE);
	}

    /**
     * SecurityParam role을 가지고 있는지 체크
     *
     * @return true :[설명], false:[설명]
     * @since 2015. 4. 9
     */
    @SuppressWarnings("unchecked")
    public static boolean hasRole(SecurityParam sp) {
		boolean hasRole = false;
		try {
			if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
					&& SecurityContextHolder.getContext().getAuthentication().getAuthorities() != null) {
				Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
						.getAuthentication().getAuthorities();

				for (GrantedAuthority authority : authorities) {
					hasRole = authority.getAuthority().equals(sp.toString());
					if (hasRole) {
						break;
					}
				}
			}
		} catch (Exception e) {
			log.debug("=======================================");
			log.debug("hasRole() error occour :: " + e.getMessage());
			log.debug("=======================================");
		}
		return hasRole;
    }

    /**
     * Gets current request.
     *
     * @return the current request
     */
	public static HttpServletRequest getCurrentRequest() {
		HttpServletRequest request = null;
		try {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			request = attributes.getRequest();
/*
			if ( request.getSession() != null ) {
                if ( request.getSession().getAttribute("SESSION_ID") == null) {
                    if ( request.getRequestedSessionId() != null ) {
                        request.getSession().setAttribute("SESSION_ID" , request.getRequestedSessionId());
                    }
                }
            }
*/
		} catch(IllegalStateException ex){
        } catch (Exception e) {
			log.debug("getCurrentRequest() Error : {}", e);
		}
		return request;
	}

    /**
     * 파일업로드 중복 파일명을 무시하고 오버라이트 업로드한다.
     *
     * @param files             the files
     * @param savePath          the save path
     * @param excludeExtensions the exclude extensions
     * @param s3Bucket          the s 3 bucket
     * @return 저장된 파일정보 List
     */
    public static FileUploadResult fileUploadOverWrite(List<MultipartFile> files, String savePath, String[] excludeExtensions , S3Bucket... s3Bucket) {
		return fileUpload(false, files, savePath, excludeExtensions, null, s3Bucket.length > 0 ? s3Bucket[0] : null );
	}

	public static FileUploadResult fileUploadNonS3(List<MultipartFile> files, String savePath, String[] excludeExtensions) {
		return fileUploadNonS3(false, files, savePath, excludeExtensions, null);
	}


    /**
     * 파일업로드 유일한 파일명을 생성하여 업로드한다.
     *
     * @param files             the files
     * @param savePath          the save path
     * @param excludeExtensions the exclude extensions
     * @param s3Bucket          the s 3 bucket
     * @return 저장된 파일정보 List
     */
    public static FileUploadResult fileUploadAutoFileName(List<MultipartFile> files, String savePath, String[] excludeExtensions, S3Bucket... s3Bucket) {
		return fileUpload(true, files, savePath, excludeExtensions, null, s3Bucket.length > 0 ? s3Bucket[0] : null );
	}

    /**
     * 2020-08-26
     * 작성자 : 박현태
     * 네이버 EP 관련 .txt 파일 업로드시 지정한 파일명으로 생성 되게  isAutoFileName 파라미터 추가하여 false로 설정
     */
    public static FileUploadResult fileUploadAutoFileName(boolean isAutoFileName, List<MultipartFile> files, String savePath, String[] excludeExtensions, S3Bucket... s3Bucket) {
		return fileUpload(isAutoFileName, files, savePath, excludeExtensions, null, s3Bucket.length > 0 ? s3Bucket[0] : null );
	}

    /**
     * File Upload 자동으로 파일명을 생성하여 AWS cloud에 저장한다.
     * 기존의 fileUpload를 수정하기에는 static 제한을 제거함에 따라 다수의 클래스를 수정해야 함에 따라 불가피하게 별도의 메서드로
     *
     * @param isAutoFileName    the is auto file name
     * @param files             List<MultipartFile>
     * @param savePath          레파지토리 경로
     * @param excludeExtensions the exclude extensions
     * @param saveFileName      the save file name
     * @param s3Bucket          S3 Bucket
     * @return 저장된 파일정보 List
     */
    public FileUploadResult fileUploadToCloud(boolean isAutoFileName, List<MultipartFile> files, String savePath, String[] excludeExtensions, String saveFileName, S3Bucket... s3Bucket) {
		return fileUpload(isAutoFileName, files, savePath, excludeExtensions, saveFileName, s3Bucket);
	}

	/**
	 * File Upload 자동으로 파일명을 생성하여 저장한다.
	 *
	 * @param isAutoFileName 파일업로드 자동부여 여부 : true(자동) , false (오리지널파일명)
	 * @param files List<MultipartFile>
	 * @param savePath 레파지토리 경로
	 * @param excludeExtensions 업로드 불가 확장자
	 * @return 저장된 파일정보 List @대량주문 엑셀일괄등록 양식
	 */
	private static FileUploadResult fileUpload(boolean isAutoFileName, List<MultipartFile> files, String savePath, String[] excludeExtensions, String saveFileName, S3Bucket... s3Bucket) {

		try {
		    S3Bucket bucket = s3Bucket != null ? s3Bucket[0] : null;

			// 확장자 체크
			availableUploadExcludeExtension(files, excludeExtensions);

			// system 제외 확장자 로직 추가해야함.
			FileUploadResult fileuploadResult = new FileUploadResult();
			List<FileUploadInfo> uploadFileList = new ArrayList<FileUploadInfo>();

			int count = 1;
			for (MultipartFile file : files) {

				String extension = getExtension(file.getOriginalFilename());

				long fileSize = file.getSize();
				String fileName = file.getOriginalFilename();
				String orgFileName = extractFileName(file.getOriginalFilename());

				if (fileSize > 0 && StringService.isNotEmpty(fileName)) {
					log.debug("= file avaiable !!!");

                    if (isAutoFileName) {
						fileName = getUniqueFileName() + "." + extension;
					} else if (saveFileName != null) {
						fileName = saveFileName;
					}
                    CloudFileSystemInterface cfs = ApplicationContextProvider.getContext().getBean(CloudFileSystemInterface.class);

                    /**
                     * fortify 오탐 - fileName이 외부입력에 의한 것이 아니라, spring프레임워크의 getOriginalFilename()로 가져온것
                     */
                    //File sourceFile = new File(saveUploadFile(file,  "/cont/temp" , fileName));
                    String uploadTemp = "/cont/temp";
                    WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                    if (context != null) {
                    	Environment env = context.getEnvironment();
                    	String temp = env.getProperty("base.upload.temp");
                    	if (uploadTemp != null)
                    		uploadTemp = temp;
                    }

                    if(savePath != null) {
                        if(savePath.contains("/cont/web/attach_img/temp")) {
                        	uploadTemp = savePath;
                        }

                    }

                    File sourceFile = new File(saveUploadFile(file, uploadTemp, fileName));
                    cfs.store(sourceFile, cfs.getCloudPath( bucket, savePath, fileName));
                    String sourcePath = sourceFile.getPath().replaceAll( "\\\\" , "/");
					String cloudUrl = cfs.getUrl(bucket.getName() , sourcePath.substring(1) );
					log.debug("url:{}", cloudUrl);
					log.debug("Cloud Path :{}",cfs.getCloudPath( bucket, savePath, fileName));

					FileUploadInfo fileUploadInfo = new FileUploadInfo();
					fileUploadInfo.setFileName(fileName);
					fileUploadInfo.setFilePath(savePath);
					fileUploadInfo.setFileSize(fileSize);
					fileUploadInfo.setOrgFileName(orgFileName);
					fileUploadInfo.setExtension(extension);
					fileUploadInfo.setCloudFilePath(cfs.getCloudPath( bucket, savePath, ""));
					fileUploadInfo.setCloudUrl(cloudUrl);

					uploadFileList.add(fileUploadInfo);

					log.debug("IOService.fileUpload[{}] orgFileName:{}, extension:{}, filesize:{}, filepath:{}, filename:{}",
							count++, fileUploadInfo.getOrgFileName(), fileUploadInfo.getExtension(), fileUploadInfo.getFileSize()
							,fileUploadInfo.getFilePath(), fileUploadInfo.getFileName());

					// spring.profiles.active이 없어 default profile이 적용될 때 에러가 발생할 수 있으니 수정함
//					if ( !"local".equals(System.getProperty("spring.profiles.active").toLowerCase())) {
//					    deleteFile(savePath, fileName );
//                    }
					if(context != null) { // local 에서 context 가 null 이라 임시조치
	                    if (!context.getEnvironment().acceptsProfiles(Profiles.of("local"))) {
	                        deleteFile(savePath, fileName );
	                    }
					}
				}

				fileuploadResult.setFileCnt(uploadFileList.size());
				fileuploadResult.setRows(uploadFileList);

			}
			return fileuploadResult;
		} catch (Exception e) {
			log.debug("", e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static FileUploadResult fileUploadNonS3(boolean isAutoFileName, List<MultipartFile> files, String savePath, String[] excludeExtensions, String saveFileName) {
		try {
			log.debug("IOService.FileUploadResult start");
			// 확장자 체크
			availableUploadExcludeExtension(files, excludeExtensions);

			// system 제외 확장자 로직 추가해야함.
			FileUploadResult fileuploadResult = new FileUploadResult();
			List<FileUploadInfo> uploadFileList = new ArrayList<FileUploadInfo>();

			int count = 1;
			for (MultipartFile file : files) {

				//확장자 추출
				String extension = getExtension(file.getOriginalFilename());

				long fileSize = file.getSize();
				String fileName = file.getOriginalFilename();
				String orgFileName = extractFileName(file.getOriginalFilename());

				if (fileSize > 0 && StringService.isNotEmpty(fileName)) {
					log.debug("= file avaiable !!!");

					if (isAutoFileName) {
						fileName = getUniqueFileName() + "." + extension;
					} else if (saveFileName != null) {
						fileName = saveFileName + "." + extension;
					}
					log.debug("IOService.FileUploadResult saveUploadFile start savePath : {}, fileName : {}",savePath,fileName);
					saveUploadFile(file, savePath, fileName);
					log.debug("IOService.FileUploadResult saveUploadFile end");

					FileUploadInfo fileUploadInfo = new FileUploadInfo();
					fileUploadInfo.setFileName(fileName);
					fileUploadInfo.setFilePath(savePath);
					fileUploadInfo.setFileSize(fileSize);
					fileUploadInfo.setOrgFileName(orgFileName);
					fileUploadInfo.setExtension(extension);

					uploadFileList.add(fileUploadInfo);

					log.debug("IOService.FileUploadResult IOService.fileUpload[{}] orgFileName:{}, extension:{}, filesize:{}, filepath:{}, filename:{}",
							count++, fileUploadInfo.getOrgFileName(), fileUploadInfo.getExtension(), fileUploadInfo.getFileSize()
							,fileUploadInfo.getFilePath(), fileUploadInfo.getFileName());
				}

				fileuploadResult.setFileCnt(uploadFileList.size());
				fileuploadResult.setRows(uploadFileList);
			}
			log.debug("IOService.FileUploadResult end");
			return fileuploadResult;
		} catch (Exception e) {
			log.debug("", e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

    /**
     * 파일 삭제
     *
     * @param filePath the file path
     * @param fileName @ @author ys.tam
     * @since 2015. 4. 29
     */
    public static void deleteFile(String filePath, String fileName) {

        /**
         * fortify - 경로 조작 및 자원삽입
         */
        SecureCode.relativePathCheck(filePath);
        SecureCode.fileNameCheck(fileName);

		File file = new File(filePath, fileName);

		if (!file.isDirectory()) {
			log.debug(" file delete =>" + file.getAbsolutePath());
			FileUtils.deleteQuietly(file);
		}
	}

    /**
     * 서버 저장된 이미지 첨부파일 미리보기
     *
     * @param response    the response
     * @param imgFilePath the img file path
     * @throws Exception the exception
     */
    public void viewImageFile(HttpServletResponse response, String imgFilePath) throws Exception{
		response.setContentType("image/jpeg");
		OutputStream out = null;

		try {
			log.debug("viewImageFile imgFilePath : {}", imgFilePath);
			out = response.getOutputStream();
			InputStream input = cloudFileSystem.getInputStream(imgFilePath);
			IOUtils.copy(input, out);

		} catch (Exception e) {
			log.warn("viewImageFile Exception : {}", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * 파입 업로드를 위한 파일 이름 생성
	 *
	 * @return
	 */
	private static String getUniqueFileName() {
		return String.valueOf(System.nanoTime());
	}

	/**
	 * 파일명를 추출한다. ( ex) kkk.pdf return kkk )
	 *
	 * @param fileName
	 * @return
	 */
	private static String extractFileName(String fileName) {

		// 나중에 lastIndex사용

		String temp[] = fileName.split("[.]");
		if (temp.length != 0)
			return temp[0];
		return "";
	}

	public static String getImageBase64String(String fileName, InputStream inputStream ) throws Exception{
		String encodedString = "";

		String ext = IOService.getExtension(fileName);
		ext = ext.toLowerCase();
		if( "jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext) || "gif".equals(ext) || "jpe".equals(ext)){
			byte[] byteArray = IOUtils.toByteArray(inputStream);
			inputStream.read(byteArray, 0, byteArray.length);
			inputStream.close();
			encodedString= "data:image/"+ext+";base64,";
			encodedString += Base64.encodeBase64String(byteArray);
		}

		return encodedString;
	}

}

