class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }
        "/download/$root/$path**"(controller: "file")
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
