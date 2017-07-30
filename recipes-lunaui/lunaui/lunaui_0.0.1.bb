SUMMARY = "Luna UI WebSite"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=01d6f96ddf706e595e24681c212042cb"

SRC_URI = "git://github.com/ProyectoLuna/luna-ui;protocol=git;rev=master"


S = "${WORKDIR}/git"

inherit npm-install

do_npm_install() {
    cd ${S}
    
    oe_runnpm_native install -g ionic@latest cordova@latest
    oe_runnpm_native install # Installs dependencies defined in package.json

}

do_install() {
 #   mkdir -p ./www

   ionic cordova platform add browser
   ionic cordova build browser
}

