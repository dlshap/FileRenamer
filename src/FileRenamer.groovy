class FileRenamer {
    static main(args) {
        def REALRUN = true
        def dir = new File("S:\\Movies\\Playon Server\\TV\\Anthony Bourdain - Parts Unknown")
        dir.eachFile {
            def path = it.getParent()
            def oldname = it.getName()
//            if (oldname[0] == "0")
//                newname = "5"+oldname[1..-1]
//            else newname = "60"+oldname[2..-1]
            def pat = /Anthony Bourdain_ (.*)\u0024/
            def matcher = oldname =~ pat
            def newname
//            println "b:${matcher.size()}"
            if (matcher.size() == 1) {
//                println "s:${matcher[0].size()}"
                if (matcher[0].size() == 2) {
                    newname = matcher[0][1]
                    println "$it .renameTo(${path + "\\" + newname})"
                    if (REALRUN)
                        it.renameTo("${path + "\\" + newname}")
                }
            }
        }
    }
}
