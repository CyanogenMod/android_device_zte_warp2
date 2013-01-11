USE_CAMERA_STUB := true

# inherit from the proprietary version
-include vendor/zte/warp2/BoardConfigVendor.mk

TARGET_NO_BOOTLOADER := true
TARGET_BOARD_PLATFORM := msm7x30
TARGET_CPU_ABI := armeabi-v7a
TARGET_CPU_ABI2 := armeabi
TARGET_BOOTLOADER_BOARD_NAME := warp2
TARGET_ARCH_VARIANT := armv7-a-neon
TARGET_CPU_SMP := true
ARCH_ARM_HAVE_TLS_REGISTER := true
TARGET_GLOBAL_CFLAGS += -mfpu=neon -mfloat-abi=softfp
TARGET_GLOBAL_CPPFLAGS += -mfpu=neon -mfloat-abi=softfp
TARGET_BOARD_PLATFORM_GPU := qcom-adreno200
BOARD_USES_ADRENO_200 := true
#TARGET_USES_ION := false
TARGET_PROVIDES_INIT_RC := true

BOARD_KERNEL_CMDLINE := console=ttyMSM1,115200,n8 androidboot.hardware=qcom vmalloc=512M kgsl.ptcount=14
BOARD_KERNEL_BASE := 0x00200000
BOARD_KERNEL_PAGESIZE := 4096

TARGET_KERNEL_CONFIG := warp2_defconfig
TARGET_PREBUILT_KERNEL := device/zte/warp2/kernel
TARGET_KERNEL_CUSTOM_TOOLCHAIN := arm-eabi-4.4.3

# fix this up by examining /proc/mtd on a running device
BOARD_BOOTIMAGE_PARTITION_SIZE := 16777216
BOARD_RECOVERYIMAGE_PARTITION_SIZE := 16777216
BOARD_SYSTEMIMAGE_PARTITION_SIZE := 520167424
BOARD_USERDATAIMAGE_PARTITION_SIZE := 2757529600
BOARD_CACHEIMAGE_PARTITION_SIZE := 313737216
BOARD_PERSISTIMAGE_PARTITION_SIZE := 8241152 
BOARD_FLASH_BLOCK_SIZE := 131072

# PARTITION LAYOUT/INFO ##
BOARD_HAS_NO_MISC_PARTITION := false
BOARD_BOOT_DEVICE := /dev/block/mmcblk0p12
BOARD_RECOVERY_DEVICE := /dev/block/mmcblk0p13
BOARD_SYSTEM_DEVICE := /dev/block/mmcblk0p15
BOARD_SYSTEM_FILESYSTEM := ext4
BOARD_CACHE_DEVICE := /dev/block/mmcblk0p18
BOARD_CACHE_FILESYSTEM := ext4
BOARD_DATA_DEVICE := /dev/block/mmcblk0p19
BOARD_DATA_FILESYSTEM := ext4
TARGET_USERIMAGES_USE_EXT4 := true

BOARD_HAS_LARGE_FILESYSTEM := true

BOARD_USES_GENLOCK := true

BOARD_SDCARD_DEVICE_PRIMARY := /dev/block/mmcblk1p1
BOARD_SDCARD_DEVICE_SECONDARY := /dev/block/mmcblk1
BOARD_SDEXT_DEVICE := /dev/block/mmcblk1p2
BOARD_USES_MMCUTILS := true

# recovery
TARGET_PREBUILT_KERNEL := device/zte/warp2/recovery/kernel
TARGET_PREBUILT_RECOVERY_KERNEL := device/zte/warp2/recovery/kernel
TARGET_RECOVERY_INITRC := device/zte/warp2/recovery/recovery.rc
BOARD_CUSTOM_GRAPHICS := ../../../device/zte/warp2/recovery/graphics.c

BOARD_HAS_NO_SELECT_BUTTON := true
#TARGET_RECOVERY_PIXEL_FORMAT := "RGB565"
ADDITIONAL_DEFAULT_PROPERTIES += ro.secure=0
ADDITIONAL_DEFAULT_PROPERTIES += ro.allow.mock.location=0
ADDITIONAL_DEFAULT_PROPERTIES += ro.debuggable=1
ADDITIONAL_DEFAULT_PROPERTIES += persist.service.adb.enable=1
ADDITIONAL_DEFAULT_PROPERTIES += ro.config.sec_storage=1
ADDITIONAL_DEFAULT_PROPERTIES += persist.sys.usb.config=mtp,adb

#BOARD_USE_USB_MASS_STORAGE_SWITCH := true
BOARD_CUSTOM_USB_CONTROLLER := ../../device/zte/arthur/UsbController.cpp
TARGET_USE_CUSTOM_LUN_FILE_PATH := "/sys/devices/platform/msm_hsusb/gadget/lun0/file"
BOARD_UMS_LUNFILE := "/sys/devices/platform/msm_hsusb/gadget/lun0/file"

# Use this flag if the board has a ext4 partition larger than 2gb
BOARD_HAS_LARGE_FILESYSTEM := true
