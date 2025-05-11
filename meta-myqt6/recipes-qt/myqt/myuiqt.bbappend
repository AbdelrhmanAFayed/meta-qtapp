#SRC_URI:append = " file://qt.service"
#SYSTEMD_AUTO_ENABLE = "enable"
#SYSTEMD_SERVICE:${PN} = "qt.service"
#
#do_install:append() {
#    install -d ${D}${systemd_unitdir}/system
#    install -m 0644 ${WORKDIR}/qt.service ${D}${systemd_unitdir}/system/
#}
