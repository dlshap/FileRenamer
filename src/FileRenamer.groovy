class FileRenamer {

    Boolean REALRUN = false

    Boolean REPLACE = false

    def pathName = "D:\\PLEX Home Videos\\Silent Home Movies\\70s"

    String path, oldname, newname

    def renameWithReplace() {
        newname = oldname.replaceAll(/75 /, "70s-")
    }

    def renameWithRegex() {
        def pat = /(.*?) (\d\d).MP4/
        def matcher = oldname =~ pat
        if (matcher.size() == 1) {
            if (matcher[0].size() >= 2) {
                newname = "${matcher[0][1]}${(sprintf("%02d", (matcher[0][2]).toInteger() + 1))}xxx .mp4"
//                newname = "${matcher[0][2]} ${matcher[0][1]}.mp4"
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
