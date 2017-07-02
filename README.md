# meta-luna
# Target filesystems path
gvim meta/conf/bitbake.conf

# Añadir paquetes a la instalación
gvim meta-luna/conf/layer.conf

TU_PAQUETE = " \
    tu_target1 \
    tu_target2 \
"

IMAGE_INSTALL_append += " \
        .
        .
    $(TU_PAQUETE) \
        .
        .
"

# Flashear SD
sudo umount /dev/mmcblk1p1
sudo umount /dev/mmcblk1p2
export MACHINE=raspberrypi2
export OETMP=/opt/yocto/poky/rpi-build/tmp
cd ./meta-rpi/scripts
./copy_boot.sh mmcblk1
./copy_rootfs.sh mmcblk1 qt5
