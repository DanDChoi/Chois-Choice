package choi.choice.framework.data.mobile;

public interface MobileDevice {
    /**
   	 * True if this device is not a mobile or tablet device.
   	 */
   	boolean isNormal();

   	/**
   	 * True if this device is a mobile device such as an Apple iPhone or an
   	 * Nexus One Android. Could be used by a pre-handle interceptor to redirect
   	 * the user to a dedicated mobile web site. Could be used to apply a
   	 * different page layout or stylesheet when the device is a mobile device.
   	 */
   	boolean isMobile();

   	/**
   	 * True if this device is a tablet device such as an Apple iPad or a
   	 * Motorola Xoom. Could be used by a pre-handle interceptor to redirect the
   	 * user to a dedicated tablet web site. Could be used to apply a different
   	 * page layout or stylesheet when the device is a tablet device.
   	 */
   	boolean isTablet();

   	/**
   	 *
   	 * @return resolved DevicePlatform
   	 */
   	MobileDevicePlatform getDevicePlatform();

   	MobileDeviceType getDeviceType();
}
