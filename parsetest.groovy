def line = """ABC this is a test
ABC and more ABC and

a little more ABC and finally
some more"""

def regex = /(?s)(.*?)ABC|(.*)/

def result = line =~ regex

result.eachWithIndex { it, i ->
    println "outer: $i:"
    it.eachWithIndex { text, j ->
        println "$j: $text"}
    }