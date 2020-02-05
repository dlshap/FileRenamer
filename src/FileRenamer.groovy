class FileRenamer {

    Boolean REALRUN = true

    Boolean REPLACE = true

    def pathName = "E:/Movies/PLEX Server/TV/Dr. Who/"

    String path, oldname, newname

    def renameWithReplace() {
        newname = oldname.replaceAll(/Bonus s06/, "Bonus s01")
    }

    def renameWithRegex() {
        def pat = /(.*?)( Series 6.*?Bonus #)(\d)(.*)/
        def matcher = oldname =~ pat
        if (matcher.size() == 1) {
            if (matcher[0].size() >= 2) {
                newname = "Doctor Who Bonus s06e${(sprintf("%02d", (matcher[0][3]).toInteger()))}.mp4"
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
            if (newname) {
                println "$it renamed to ${path + "/" + newname}"
                if (REALRUN)
                    it.renameTo("${path + "/" + newname}")
            }
        }
    }
}
