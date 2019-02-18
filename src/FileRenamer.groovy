class FileRenamer {
    static main(args) {

        def REALRUN = true

        def pathName = "S:\\Movies\\PLEX Server\\Home Videos\\Home Movies\\1957-1960"

        def dir = new File(pathName)
        dir.eachFile {
            def path = it.getParent()
            def oldname = it.getName()
            def pat = /(.*? ).*?_(\d*).mp4/
            def matcher = oldname =~ pat
            def newname
            if (matcher.size() == 1) {
                if (matcher[0].size() >= 2) {
//                    def newNum = (matcher[0][1]).toInteger()+2
//                    def newNumString = sprintf("%02d", newNum)
//                    newname = "${matcher[0][1]}${sprintf("%02d",(matcher[0][2]).toInteger()+2)} xxx .mp4"
                    newname = "${matcher[0][1]}${sprintf("%02d",(matcher[0][2]).toInteger()+2)}- xxx .mp4"
                    println "$it .renameTo(${path + "\\" + newname})"
                    if (REALRUN)
                        it.renameTo("${path + "\\" + newname}")
                }
            }
        }
    }
}
