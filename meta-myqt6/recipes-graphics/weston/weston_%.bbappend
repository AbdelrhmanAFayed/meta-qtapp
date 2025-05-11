FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://weston-rdp.service"

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/weston-rdp.service ${D}${systemd_system_unitdir}/
}

# Include the service file in the package
FILES:${PN} += "${systemd_system_unitdir}/weston-rdp.service"
SYSTEMD_SERVICE:${PN} = "weston-rdp.service"