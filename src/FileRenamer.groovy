class FileRenamer {
    static main(args) {
        def dir = new File("D:\\My Music\\For iTunes\\Headspace\\Videos\\Getting Started")
        dir.eachFile {
            def path = it.getParent()
            def oldname = it.getName()
//            if (oldname[0] == "0")
//                newname = "5"+oldname[1..-1]
//            else newname = "60"+oldname[2..-1]
            def pat = /(HS )(.*)/
            def matcher = oldname =~ pat
            def newname
//            println "b:${matcher.size()}"
            if (matcher.size() == 1) {
//                println "s:${matcher[0].size()}"
                if (matcher[0].size() == 3) {
                    newname = "HS" + matcher[0][2]
                    println "$it .renameTo(${path + "\\" + newname})"
//                    it.renameTo("${path + "\\" + newname}")
                }
            }
        }
    }
}
