class FileRenamer {

    def REALRUN = true

    def REPLACE = true

    def pathName = "S:\\Movies\\PLEX Server\\Home Videos\\Home Movies\\1957-1960"
    String path, oldname, newname

    def renameWithReplace() {
        newname = oldname.replaceAll(/1957-60/, "1957-1959")
    }

    def renameWithRegex() {
        def pat = /(.*? ).*?_(\d*).mp4/
        pat = /(.*?)(\d\d-)(.*)/
        def matcher = oldname =~ pat
        if (matcher.size() == 1) {
            if (matcher[0].size() >= 2) {
                newname = "${matcher[0][1]}${sprintf("%02d", (matcher[0][2]).toInteger() + 2)}- xxx .mp4"
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
