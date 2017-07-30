SUMMARY = "Newly Optimized RF24 NRF24L01 Radio Library for Arduino"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a23a74b3f4caf9616230789d94217acb"
#DEPENDS = "Package list of build time dependencies"

SRC_URI = "git://github.com/ProyectoLuna/RF24.git;protocol=git;rev=master"
#SRC_URI[md5sum] = "5dc1af34d6fda442861bc39bfa5eda57"
#SRC_URI[sha256sum] = "4c5aa5e93234526f5df4b4d4c080cf3b3cda3ea15696dfb2afe53a722a492c9b"

S = "${WORKDIR}/git"

inherit pkgconfig
# Task overrides, like do_configure, do_compile and do_install, or nothing.
# Package splitting (if needed).
# Machine selection variables (if needed).

LIBNAME = "lib${PN}.so.${PV}"
LIBDEPRECATE="librf24-bcm.so.${PV}"

INSANE_SKIP_${PN} = "ldflags"

do_configure() {
    ./configure --driver=RPi
}

do_compile() {
    make
}

do_install() {
    cp ${LIBNAME} ${LIBDEPRECATE}
    install -d ${D}${libdir}
    oe_soinstall ${LIBNAME} ${D}${libdir}
    oe_soinstall ${LIBDEPRECATE} ${D}${libdir}

    install -d ${D}${includedir}/RF24/utility/RPi
    install -m 0644 *.h ${D}${includedir}/RF24
    install -m 0644 utility/RPi/*.h ${D}${includedir}/RF24/utility/RPi
    install -m 0644 utility/*.h ${D}${includedir}/RF24/utility
}

FILES_${PN} += " \
                ${includedir}/RF24/*.h \
                ${includedir}/RF24/utility/*.h \
                ${includedir}/RF24/utility/RPi/*.h \
                "
FILES_${PN}-dev += " \
                ${includedir}/RF24/*.h \
                ${includedir}/RF24/utility/*.h \
                ${includedir}/RF24/utility/RPi/*.h \
                "
