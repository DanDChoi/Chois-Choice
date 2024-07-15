package choi.choice.framework.data.mobile;

public class LiteMobileDevice implements MobileDevice{
    public static final LiteMobileDevice NORMAL_INSTANCE = new LiteMobileDevice(MobileDeviceType.NORMAL,
   			MobileDevicePlatform.UNKNOWN);

   	public static final LiteMobileDevice MOBILE_INSTANCE = new LiteMobileDevice(MobileDeviceType.MOBILE,
   			MobileDevicePlatform.UNKNOWN);

   	public static final LiteMobileDevice TABLET_INSTANCE = new LiteMobileDevice(MobileDeviceType.TABLET,
   			MobileDevicePlatform.UNKNOWN);

   	public boolean isNormal() {
   		return this.deviceType == MobileDeviceType.NORMAL;
   	}

   	public boolean isMobile() {
   		return this.deviceType == MobileDeviceType.MOBILE;
   	}

   	public boolean isTablet() {
   		return this.deviceType == MobileDeviceType.TABLET;
   	}

   	@Override
   	public MobileDevicePlatform getDevicePlatform() {
   		return this.devicePlatform;
   	}

   	@Override
   	public MobileDeviceType getDeviceType() {
   		return this.deviceType;
   	}

   	public static MobileDevice from(MobileDeviceType deviceType, MobileDevicePlatform devicePlatform) {
   		return new LiteMobileDevice(deviceType, devicePlatform);
   	}

   	@Override
   	public String toString() {
   		StringBuilder builder = new StringBuilder();
   		builder.append("[LiteDevice ");
   		builder.append("type").append("=").append(this.deviceType);
   		builder.append("]");
   		return builder.toString();
   	}

   	private final MobileDeviceType deviceType;

   	private final MobileDevicePlatform devicePlatform;

   	/**
   	 * Creates a LiteDevice with DevicePlatform.
   	 */
   	private LiteMobileDevice(MobileDeviceType deviceType, MobileDevicePlatform devicePlatform) {
   		this.deviceType = deviceType;
   		this.devicePlatform = devicePlatform;
   	}
}
