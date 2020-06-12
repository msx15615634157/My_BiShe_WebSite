function loginout() {
    fetch("loginout.do").then(res=>res.json()).then(function (value) {
        console.log(value)
        location.reload()

    })
}