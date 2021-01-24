import groovy.io.FileType

class FileRenamer {

    Boolean REALRUN = false

    Boolean REPLACE = false

    def pathName = "D:\\My Music\\For iTunes\\Headspace\\Pro 1-6"

    String path, oldname, newname

    static main(args) {
        new FileRenamer()
    }

    FileRenamer() {
        start()
    }

    def start() {
        def topDir = new File(pathName)
        renameFiles(topDir)
    }

    def renameFiles(subpath) {
        println "${subpath}:\n\r"
        def dir = new File(subpath)
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

    def renameWithReplace() {
        newname = oldname.replaceAll(/Bonus s06/, "Bonus s01")
    }

    def renameWithRegex() {
//        def pat = /(.*?)( Series 6.*?Bonus #)(\d)(.*)/
//        def pat = /(\d\d)-(\d\d)-(\d\d)(.*)/
//        def pat = /(\d\d)-(\d\d) ([a-z,A-Z]{3})[^ ]?(.*)?/
//        def monthNames = ["jan": "01", "feb": "02", "mar": "03", "apr": "04", "may": "05", "jun": "06",
//                          "jul": "07", "aug": "08", "sep": "09", "oct": "10", "nov": "11", "dec": "12"]

        def pat = /(\d\d-Pro Level 6-)(\d{1,2})(.mp3)/

        def matcher = oldname =~ pat
        if (matcher.size() == 1) {
            if (matcher[0].size() >= 2) {
//                def monthNum = monthNames[((matcher[0][3]).toString().toLowerCase())]
//                newname = "${matcher[0][1]}${monthNum}${matcher[0][2]}${matcher[0][4]}"
                newname = "${matcher[0][1]}${matcher[0][2]}x${matcher[0][3]}"
            }
        }
    }
}
