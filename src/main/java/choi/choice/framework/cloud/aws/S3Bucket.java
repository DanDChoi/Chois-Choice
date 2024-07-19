package choi.choice.framework.cloud.aws;

public enum S3Bucket {

    STATIC(CloudVariable.staticBucket , "PC 정적 컨텐츠(css,js..) 버킷"),
    PRODUCTS(CloudVariable.productBucket , "컨테츠용 버킷"  ),
    CONTENTS(CloudVariable.contentBucket , "컨테츠용 버킷"  ),
    PUBLIC_ATTACH(CloudVariable.attachPublicBucket , "공개 청부파일용 버킷" ),
    PRIVATE_ATTACH(CloudVariable.attachPrivateBucket , "비공개 첨부파일용 버킷" ),
    PUBLIC_EP(CloudVariable.epBucket , "네이버 EP 공개파일용 버킷" ),
    PUBLIC_XML(CloudVariable.xmlBucket , "XML 공개파일용 버킷" ),
    ;

    private String name;
    private String description;

    private static final String DELIMETER = ":";

    S3Bucket(String name , String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getNameWithDelimeter() {
        return name + DELIMETER;
    }

    public String getDescription() {
        return description;
    }
}
