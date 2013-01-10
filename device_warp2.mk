$(call inherit-product, $(SRC_TARGET_DIR)/product/languages_full.mk)

# The gps config appropriate for this device
$(call inherit-product, device/common/gps/gps_us_supl.mk)

$(call inherit-product-if-exists, vendor/zte/warp2/warp2-vendor.mk)

DEVICE_PACKAGE_OVERLAYS += device/zte/warp2/overlay

LOCAL_PATH := device/zte/warp2
ifeq ($(TARGET_PREBUILT_KERNEL),)
	LOCAL_KERNEL := $(LOCAL_PATH)/kernel
else
	LOCAL_KERNEL := $(TARGET_PREBUILT_KERNEL)
endif

PRODUCT_COPY_FILES += \
	$(LOCAL_PATH)/recovery/rmt_storage_recovery:recovery/root/sbin/rmt_storage_recovery \
	$(LOCAL_PATH)/recovery/ueventd.rc:recovery/root/ueventd.rc \
	$(LOCAL_PATH)/recovery/usbconfig:recovery/root/sbin/usbconfig

$(call inherit-product, build/target/product/full.mk)

PRODUCT_BUILD_PROP_OVERRIDES += BUILD_UTC_DATE=0
PRODUCT_NAME := full_warp2
PRODUCT_DEVICE := warp2
