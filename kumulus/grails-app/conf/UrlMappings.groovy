class UrlMappings {

	static mappings = {
        "/"{
            controller = "login"
        }
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }
        "500"(view:'/error')
	}
}
