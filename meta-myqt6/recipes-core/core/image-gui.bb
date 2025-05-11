SUMMARY = "this will create GUI image with QT example"

LICENSE = "CLOSED"

inherit core-image autotools cmake 

DEPENDS = " myuiqt qemuwrapper-cross weston weston-init freerdp udev systemd bash "

RDEPENDS:${PN} = " connman-client"
# DEPENDS += "glibc-locale"


IMAGE_FEATURES = "splash  weston package-management"

# IMAGE_INSTALL:append = " weston weston-init freerdp udev systemd bash connman-client"




# IMAGE_INSTALL:append = " glibc-locale"
# IMAGE_INSTALL:append = " glibc"
# 
# IMAGE_INSTALL:append = " glibc-utils"

IMAGE_INSTALL:append = " myuiqt"


IMAGE_ROOTFS_EXTRA_SPACE = "5242880"

# Ensure root password is empty
ROOTFS_POSTPROCESS_COMMAND:append = " set_empty_root_password; generate_rdp_cert; "



SYSTEMD_SERVICE:${PN} += "systemd-networkd.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"


generate_rdp_cert() {
    install -d ${IMAGE_ROOTFS}/etc/weston
    openssl req -new -x509 -days 365 -nodes \
        -out ${IMAGE_ROOTFS}/etc/weston/rdp-cert.pem \
        -keyout ${IMAGE_ROOTFS}/etc/weston/rdp4-key.pem \
        -subj "/CN=weston-rdp"
}



set_empty_root_password() {
    sed -i 's/^root:[^:]*:/root::/' ${IMAGE_ROOTFS}/etc/shadow
}


do_install:append() {
    install -d ${D}/etc/weston
    openssl genrsa -out ${D}/etc/weston/rdp4-key.pem 2048
}

do_install:append() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/eth0.network ${D}${sysconfdir}/systemd/network/
}