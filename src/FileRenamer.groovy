class FileRenamer {
    static main(args) {

        def REALRUN = true

        def dir = new File("S:\\Movies\\PLEX Server\\Home Videos\\1993-1994\\")
        dir.eachFile {
            def path = it.getParent()
            def oldname = it.getName()
            def pat = /.*?_(\d*).mp4/
            def matcher = oldname =~ pat
            def newname
            if (matcher.size() == 1) {
                if (matcher[0].size() >= 2) {
//                    def newNum = (matcher[0][1]).toInteger()+2
//                    def newNumString = sprintf("%02d", newNum)
                    newname = "1993-${sprintf("%02d",(matcher[0][1]).toInteger()+2)} xxx .mp4"
                    println "$it .renameTo(${path + "\\" + newname})"
                    if (REALRUN)
                        it.renameTo("${path + "\\" + newname}")
                }
            }
        }
    }
}
