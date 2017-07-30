SUMMARY = "Luna UI WebSite"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=01d6f96ddf706e595e24681c212042cb"

SRC_URI = "git://github.com/ProyectoLuna/luna-ui;protocol=git;rev=master \
           file://lighttpd.conf.matrix"

S = "${WORKDIR}/git"
#B = "${S}"

inherit npm-install
inherit allarch
RDEPENDS_${PN} = "lighttpd"

do_npm_install() {
    cd ${S}

    oe_runnpm_native install -g ionic@latest cordova@latest
    oe_runnpm_native install # Installs dependencies defined in package.json
}

DEST_DIR = "/www/lunaui"

do_install() {
    ionic cordova platform add browser
    ionic cordova build browser    

    #install -d ${D}${DEST_DIR}
    echo ${D}
    install -d ${D}${DEST_DIR}
    cp -Rp ${S}/platforms/browser/www/* ${D}${DEST_DIR}
    #find ${S}/platforms/browser/www -type f -exec 'install -m 0755 "{}" ${D}${DEST_DIR}' \;
    
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/lighttpd.conf.matrix ${D}${sysconfdir}/lighttpd.conf.matrix
}

pkg_postinst_${PN} () {
    if [ -f $D${sysconfdir}/lighttpd.conf ] ; then
    	cp $D${sysconfdir}/lighttpd.conf.matrix $D${sysconfdir}/lighttpd.conf
    else
    	echo "No lighttpd.conf found, aborting"
    	exit 1
    fi
}

FILES_${PN} = "${DEST_DIR}/* ${sysconfdir}/lighttpd.conf.matrix"
