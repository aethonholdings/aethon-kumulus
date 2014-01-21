package com.kumulus.services

import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils

@Transactional
class FilesystemService {

    def generateLiteral() {
        String literal
        Date date = new Date()
        
        literal = date.format("yyyyMMddhhmmss")
        literal += RandomStringUtils.random(4, true, true).toUpperCase()
        return(literal)
    }
}