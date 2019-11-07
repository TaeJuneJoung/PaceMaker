export default function (context) {
	if (!context.$storage.getUniversal('isAuth')) {
		context.redirect('/')
	}
}
