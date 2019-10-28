export default function ({ store, route, redirect }) {
	if(!isAuth()) {
		return redirect('/')
	}
}

function isAuth() {
	// store을 쓰지 않는 방도로 고민해볼 것
	return false
}