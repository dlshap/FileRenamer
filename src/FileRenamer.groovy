class FileRenamer {

    def REALRUN = false

    def REPLACE = false

    def pathName = "D:\\PLEX Home Videos\\1996"

    String path, oldname, newname

    def renameWithReplace() {
        newname = oldname.replaceAll(/93-/, "94-")
    }

    def renameWithRegex() {
        def pat = /(96-).*_(\d*).*?.mp4/
        def matcher = oldname =~ pat
        if (matcher.size() == 1) {
            if (matcher[0].size() >= 2) {
                newname = "${matcher[0][1]}x${(sprintf("%02d", (matcher[0][2]).toInteger() + 1))}yyy .mp4"
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
