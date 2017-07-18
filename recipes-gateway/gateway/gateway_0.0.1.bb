SUMMARY = "Proyecto Luna Gateway"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "rf24 rf24network rf24mesh"

SRC_URI = "git://github.com/ProyectoLuna/gateway-radio.git;protocol=git;rev=master"

inherit cmake

S = "${WORKDIR}/git"

do_configure() {
    cmake ../git
}

do_compile() {
    make
}

do_install() {
    install -d ${D}${bindir}

    install -m 0755 proyecto-luna ${D}${bindir}
}
