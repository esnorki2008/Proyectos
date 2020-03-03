QT -= gui
QMAKE_LFLAGS += -no-pie
CONFIG += c++11 console
CONFIG -= app_bundle

# The following define makes your compiler emit warnings if you use
# any Qt feature that has been marked deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
    Fase1/fdisk.cpp \
    Fase1/fdiskadd.cpp \
    Fase1/fdiskdelete.cpp \
    Fase1/mkdisk.cpp \
    Fase1/rmdisk.cpp \
    disco.cpp \
    ext.cpp \
    functions.cpp \
        main.cpp \
    menu.cpp \
    operacionusuario.cpp \
    parser.cpp \
    recuperacion.cpp \
    reports.cpp \
    scanner.cpp \
    sistema.cpp

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

HEADERS += \
    Fase1/fdisk.h \
    Fase1/fdiskadd.h \
    Fase1/fdiskdelete.h \
    Fase1/mkdisk.h \
    Fase1/rmdisk.h \
    disco.h \
    ext.h \
    functions.h \
    menu.h \
    operacionusuario.h \
    parser.h \
    recuperacion.h \
    reports.h \
    scanner.h \
    sistema.h \
    structs.h \
    structsext.h

DISTFILES += \
    Fase1/Dummy
