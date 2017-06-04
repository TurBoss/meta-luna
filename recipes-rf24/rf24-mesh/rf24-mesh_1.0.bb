SUMMARY = "Dynamic Mesh Layer for RF24Network"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://README.md;md5=ecfd54ceeb930d752852fd1513329e79"

DEPENDS = "rf24 rf24-network"

SRC_URI = "git://github.com/nRF24/RF24Mesh.git;protocol=git;rev=master"
#SRC_URI[md5sum] = "7246eed18fbdc922c302f42b52daac0f"
#SRC_URI[sha256sum] = "375c31b52562b4d145a89790454533f0e54bb14494649e1aba7732404bc68623"

S = "${WORKDIR}/git"

# inherit <class needed for some functionality>
# Task overrides, like do_configure, do_compile and do_install, or nothing.
# Package splitting (if needed).
# Machine selection variables (if needed).

LIBNAME = "lib${PN}.so.${PV}"

INSANE_SKIP_${PN} = "ldflags"

do_compile() {
    ${CXX} -Wall -fPIC ${CCFLAGS} -c RF24Mesh.cpp
    ${CXX} -shared -Wl,-soname,librf24mesh.so.1 ${CCFLAGS} -o ${LIBNAME} RF24Mesh.o
}

do_install() {
    install -d ${D}${libdir}
    oe_soinstall ${LIBNAME} ${D}${libdir}

    install -d ${D}${includedir}/RF24Mesh
    install -m 0644 *.h ${D}${includedir}/RF24Mesh
}
