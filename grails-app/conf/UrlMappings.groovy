class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:'inicio',view:"/index")
		"500"(view:'/error')
	}
}
