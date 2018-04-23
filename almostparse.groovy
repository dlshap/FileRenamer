def line = """ABC
more 
ABC
ABC ABC
and finally some
more.
"""

def regex = /(?s)(.*)ABC/

def result = line =~ regex

println result.count

result.eachWithIndex { it, i ->
    println "$i: $it"
    }