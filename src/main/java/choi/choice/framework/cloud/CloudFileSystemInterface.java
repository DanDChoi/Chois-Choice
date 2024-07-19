package choi.choice.framework.cloud;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public interface CloudFileSystemInterface {
    enum Permission {
            /**
             * permit read permission to anonymous. (상품 이미지와 같이 모든 사용자에게 서비스할 파일의 경우 CloudFileSystem.Permission.PUBLIC으로 선언해줘야 한다.)
             */
            PUBLIC,
            /**
             * don't permit read permission to anonymous
             */
            PRIVATE
    	}

        /**
         * Permission.PRIVATE으로 {@link #store(String, String, Permission))}을 호출한다.
         *
         * @param filename  local file's full path
         * @param cloudPath cloud file's full path
         * @throws IOException the io exception
         */
        void store(String filename, String cloudPath) throws IOException;

        /**
         * local server의 file을 cloud file system으로 저장한다.
         *
         * @param filename   local file's full path
         * @param cloudPath  cloud file's full path
         * @param permission see {@link Permission}
         * @throws IOException the io exception
         */
        void store(String filename, String cloudPath, Permission permission) throws IOException;

        /**
         * Permission.PRIVATE으로 {@link #store(InputStream, long, String, String, Permission))}을 호출한다.
         *
         * @param file      the file
         * @param cloudPath cloud file's full path
         * @throws IOException the io exception
         */
        void store(File file, String cloudPath) throws IOException;

        /**
         * Permission.PRIVATE으로 {@link #store(File, String, Permission))}을 호출한다.
         *
         * @param file       the file
         * @param cloudPath  cloud file's full path
         * @param permission see {@link Permission}
         * @throws IOException the io exception
         */
        void store(File file, String cloudPath, Permission permission) throws IOException;

        /**
         * Permission.PRIVATE으로 {@link #store(InputStream, long, String, String, Permission))}을 호출한다.
         *
         * @param input                 local file's File 객체
         * @param sourceFileSize        file size(byte)
         * @param cloudPath             full path for saving cloud file system
         * @param filename              name of file for saving cloud file system.
         * @throws IOException the io exception
         */
        void store(InputStream input, long sourceFileSize, String cloudPath, String filename) throws IOException;

        /**
         * local server의 InputStream을 읽어들여 cloud file system으로 저장한다.
         * <font color="red">cloudPath에 파일이름을 포함하면 안된다.</font>
         *
         * @param input                 local file's InputStream, 자체적으로 Buffering 처리를 하므로 별도로 BufferedInputStream 처리를 할 필요가 없다.
         * @param sourceFileSize        file size(byte)
         * @param cloudPath             full path for saving cloud file system
         * @param filename              name of file for saving cloud file system.
         * @param permission            see {@link Permission}
         * @throws IOException the io exception
         */
        void store(InputStream input, long sourceFileSize, String cloudPath, String filename, Permission permission) throws IOException;

        /**
         * cloud file system의 source file을 target file로 copy한다.
         *
         * @param sourcePath source file's full path
         * @param targetPath target file's full path
         * @throws IOException the io exception
         */
        void copy(String sourcePath, String targetPath) throws IOException;

        /**
         * Permission.PRIVATE으로 {@link #copy(String, String, Permission))}을 호출한다.
         *
         * @param sourcePath source file's full path
         * @param targetPath target file's full path
         * @param permission see {@link Permission}
         * @throws IOException the io exception
         */
        void copy(String sourcePath, String targetPath, Permission permission) throws IOException;

        /**
         * Permission.PRIVATE으로 {@link #move(String, String, Permission))}을 호출한다.
         *
         * @param sourcePath source file's full path
         * @param targetPath target file's full path
         * @throws IOException the io exception
         */
        void move(String sourcePath, String targetPath) throws IOException;

        /**
         * cloud file system의 source file을 target file로 move한다.
         *
         * @param sourcePath source file's full path
         * @param targetPath target file's full path
         * @param permission see {@link Permission}
         * @throws IOException the io exception
         */
        void move(String sourcePath, String targetPath, Permission permission) throws IOException;

        /**
         * cloud file system의 file을 삭제한다.
         *
         * @param sourcePath source file's full path
         */
        void delete(String sourcePath);

        /**
         * cloud file system의 folder를 삭제한다.
         *
         * @param sourcePath source folder path
         */
        void deleteFolder(String sourcePath);

        /**
         * cloud file system의 folder의 object list를 가져온다.
         *
         * @param sourcePath the source path
         * @return list objects
         * @throws IOException the io exception
         */
        Collection<CloudFileMetadata> getListObjects(String sourcePath) throws IOException;

        /**
         * get cloudFile's metadata
         *
         * @param cloudPath full path of cloud file
         * @return the file information
         * @throws IOException the io exception
         */
        CloudFileMetadata getFileInformation(String cloudPath) throws IOException;

        /**
         * File.exist와 동일
         *
         * @param cloudPath full path of cloud file
         * @return the boolean
         */
        boolean exist(String cloudPath);

        /**
         * get InputStream of cloud file.<br>
         * 별도의 BufferedInputStream으로 추가 정의할 필요 없음.
         * <pre>
         * 처리 완료 후 close가 호출되는 것을 보장해야 한다. (ex: try-with-resource)
         * {@code
         * try (InputStream in = getInputStream(path)) {
         *    ....
         * }
         * </pre>
         *
         * @param cloudFilePath full path of cloud file
         * @param cloudFilePath the cloud file path
         * @return the input stream
         * @throws IOException*
         * @throws IOException  the io exception
         */
        InputStream getInputStream(String cloudPath) throws IOException;

        /**
         * s3의 file(sourceCloudPath)의 파일을 local server의 file(targetLocalPath)로 저장한다.
         *
         * @param sourceCloudPath full path of cloud file
         * @param targetLocalPath full path of local file. if loal file and directory doesn't exist then create it.
         * @throws IOException the io exception
         */
        void storeToLocal(String sourceCloudPath, String targetLocalPath) throws IOException;

        /**
         * Permission.PRIVATE으로 {@link #unzip(String, String, Permission))}을 호출한다.
         *
         * @param sourcePath the source path
         * @param targetPath the target path
         * @throws Exception the exception
         */
        void unzip(String sourcePath, String targetPath) throws Exception;

        /**
         * s3의 zip file(sourceCloudPath)의 파일을 targetPath로 unzip을 한다.
         *
         * @param sourcePath the source path
         * @param targetPath the target path
         * @param permission the permission
         * @throws Exception the exception
         */
        void unzip(String sourcePath, String targetPath, Permission permission) throws Exception;

        /**
         * Gets url.
         *
         * @param bucketName the bucket name
         * @param path       the path
         * @return the url
         */
        String getUrl(String bucketName, String path);

        /**
         * helper method: cloudpath에 맞게 fullpath return
         *
         * @param path     parent path
         * @param filename file name
         * @return full path
         */
        String getCloudPath( S3Bucket s3Bucket , String path, String filename);

        /**
         * helper method: preSignedUrl 생성
         *
         * @param path     parent path
         * @param filename file name
         * @return full path
         */
        String getSignedUrl (S3Bucket s3Bucket , String path , String filename);

        /**
         * cloudPath의 s3 파일의 userMedata를 update
         *
         * @param cloudPath $bucketName:/$path
         * @param userMetaData 업데이트할 udateMetadata
         * @throws IOException
         */
        void updateUserMetadata(String cloudPath, Map<String, String> userMetaData) throws IOException;
}
