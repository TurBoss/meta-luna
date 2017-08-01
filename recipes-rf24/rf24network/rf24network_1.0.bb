SUMMARY = "OSI Network Layer for multi-device communication. Create a home sensor network"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a23a74b3f4caf9616230789d94217acb"

DEPENDS = "rf24"
#DEPENDS = "rf24 bcm2835"

#inherit pkgconfig
SRC_URI = "git://github.com/ProyectoLuna/RF24Network.git;protocol=git;rev=master"
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
    ${CXX} -Wall -fPIC ${CCFLAGS} -c RF24Network.cpp
    ${CXX} -shared -Wl,-soname,librf24network.so.1 ${CCFLAGS} -o ${LIBNAME} RF24Network.o -lrf24
}

do_install() {
    install -d ${D}${libdir}
    oe_soinstall ${LIBNAME} ${D}${libdir}

    install -d ${D}${includedir}/RF24Network
    install -m 0644 *.h ${D}${includedir}/RF24Network
}

FILES_${PN} += " \
                ${includedir}/RF24Network/*.h \
                "
FILES_${PN}-dev += " \
                ${includedir}/RF24Network/*.h \
                "
