package com.kumulus.taglibs

class KumulusTagLib {
    static defaultEncodeAs = 'text'
    //static encodeAsForTags = [tagName: 'raw']
    
    def projectTable = { attrs, body ->
        out << "<table class='pure-table' width='100%'>\n"
        out << "    <thead>\n"
        out << "        <tr>\n"
        out << "            <th>Id</a>\n"
        out << "            <th>Project</th>\n"
        out << "            <th>Client</th>\n"
        out << "        </tr>\n"
        out << "    </thead>\n"
        out << "    <tbody>\n"
        out << "        <g:each in='${}' var='projectInstance'>\n"
        out << "            <tr>\n"
        out << "                <td>${}</td>\n"
        out << "                <td><g:link controller='collect' action='workflow' id='${}'>${}</g:link></td>\n"
        out << "                <td>${}</td>\n"
        out << "            </tr>\n"
        out << "        </g:each>\n"
        out << "    </tbody>\n"
        out << "</table>\n"
    }
}
