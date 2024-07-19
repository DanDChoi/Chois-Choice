package choi.choice.framework.cloud;

import lombok.Data;

@Data
public class CloudFileMetadata {
    /** file's content type */
    	private String contentType;

    	/** file's content dispostion */
    	private String contentDisposition;

    	/** file's last modified time */
    	private long lastModified;

    	/** file size (byte) */
    	private long length;

    	/**
    	 * cloud file system에 존재하는 file의 full path
    	 * s3의 경우에는 s3://$bucketName/$path에 해당한다.
    	 */
    	private String uri;

    	/**
    	 * return aws s3 etag
    	 */
    	private String eTag;

    	/**
    	 * uri의 scheme이나 host(or bucketName)을 제외한 path 만을 리턴한다.
    	 */
    	public String getPath() {
    		String path = uri;
    		int idx = uri.indexOf("://");
    		if (idx > 0) {
    			path = uri.substring(idx+"://".length());
    		}
    		idx = path.indexOf(':');
    		if (idx > 0) {
    			path = path.substring(idx+1);
    		}

    		return path;
    	}
}
