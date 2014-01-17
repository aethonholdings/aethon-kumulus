package com.kumulus.taglibs

class KumulusTagLib {
    static defaultEncodeAs = 'text'
    //static encodeAsForTags = [tagName: 'raw']
    
    def userService
    
    def projectTable = { attrs, body ->
        out << "<table class='${attrs?.class}' width='${attrs?.width}'>\n"
        out << "    <thead>\n"
        out << "        <tr>\n"
        out << "            <th>Id</a>\n"
        out << "            <th>Project</th>\n"
        out << "            <th>Client</th>\n"
        out << "            <th>Status</th>\n"
        out << "            <th>Action</th>\n"
        out << "        </tr>\n"
        out << "    </thead>\n"
        out << "    <tbody>\n"
        attrs?.projects.each {
            out << "            <tr>\n"
            out << "                <td>${it.id}</td>\n"
            out << "                <td>${it.projectName}</td>\n"
            out << "                <td>${it.clientLDAPId}</td>\n"
            out << "                <td>${it.status}</td>\n"
            out << "                <td>"
            out << g.link(action:"${attrs?.action}", controller:"${attrs?.controller}", id:"${it.id}", "${attrs?.actionText}")
            out << "</td>\n"
            out << "            </tr>\n"
        }
        out << "    </tbody>\n"
        out << "</table>\n"
    }
    
    def userCompany = { attrs, body ->
        String company = userService.company
        if(company) {
            out << company
        }
    }
    
    def pageTitle = { attrs, body ->
        if(attrs.text) {
            out << attrs.text
        }
    }
    
}
