class FileRenamer {

    def REALRUN = false

    def REPLACE = false

    def pathName = "D:\\PLEX Home Videos\\1989 Mom and Dad in Europe"

    String path, oldname, newname

    def renameWithReplace() {
        newname = oldname.replaceAll(/1989-/, "1989EU-")
    }

    def renameWithRegex() {
        def pat = /(1991-)0x xxx _(\d.*?).mp4/
        def matcher = oldname =~ pat
        if (matcher.size() == 1) {
            if (matcher[0].size() >= 2) {
                newname = "${matcher[0][1]}1${(sprintf("%02d", (matcher[0][2]).toInteger() + 2))} .mp4"
            }
        }
    }

    static main(args) {
        new FileRenamer()
    }

    FileRenamer() {
        start()
    }

    def start() {
        def dir = new File(pathName)
        dir.eachFile {
            path = it.getParent()
            oldname = it.getName()
            newname = ""
            if (REPLACE)
                renameWithReplace()
            else
                renameWithRegex()
            if (newname != "") {
                println "$it .renameTo(${path + "\\" + newname})"
                if (REALRUN)
                    it.renameTo("${path + "\\" + newname}")
            }
        }
    }
}
