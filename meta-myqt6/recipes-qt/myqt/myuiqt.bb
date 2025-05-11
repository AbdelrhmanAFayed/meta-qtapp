SRC_URI = "git://github.com/AbdelrhmanAFayed/QT6.git;branch=main;protocol=https"
SRCREV = "${AUTOREV}"
LICENSE = "CLOSED"

inherit cmake qt6-cmake qt6-paths

S = "${WORKDIR}/git/test_yocto"

DEPENDS += "qtbase qtdeclarative qtwayland qtdeclarative-native qttools "


RDEPENDS:${PN} = "qtbase qtwayland qtmultimedia qtdeclarative qttools"

EXTRA_OECMAKE = " \
    -DCMAKE_FIND_DEBUG_MODE=TRUE \
    -DQT_HOST_PATH=${STAGING_DIR_NATIVE}${prefix_native} \
"

EXTRA_OECONF += "--disable-nls"

# IMAGE_INSTALL:append = " qtwayland qtdeclarative"
# IMAGE_INSTALL:append = " libxcb libx11-xcb libxcb-cursor"
# IMAGE_INSTALL:append = " libxcb xcb-util xcb-util-image xcb-util-keysyms xcb-util-wm xcb-util-renderutil"
# IMAGE_INSTALL:append = " qtbase qtbase-plugins qtbase-tools qtwayland qtwayland-plugins libxcb xcb-util xcb-util-image xcb-util-keysyms xcb-util-wm xcb-util-renderutil xcb-util-cursor"

FILES:${PN} = "${bindir}/apptest_yocto ${datadir}/test_yocto"

# do_install() {
#     # Create the installation directory for the binary
#     install -d ${D}${bindir}
# 
#     # Install the binary
#     if [ -f "${B}/apptest_yocto" ]; then
#         install -m 0755 ${B}/apptest_yocto ${D}${bindir}/apptest_yocto
#     elif [ -f "${B}/bin/apptest_yocto" ]; then
#         install -m 0755 ${B}/bin/apptest_yocto ${D}${bindir}/apptest_yocto
#     else
#         bberror "apptest_yocto binary not found in ${B} or ${B}/bin"
#     fi
# 
#     # Install QML files
#     install -d ${D}${datadir}/test_yocto
#     if [ -f "${S}/Main.qml" ]; then
#         install -m 0644 ${S}/Main.qml ${D}${datadir}/test_yocto/
#     else
#         bberror "Main.qml not found in ${S}"
#     fi
# }