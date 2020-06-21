const TokenKey = 'EBANK_TOKEN'
if(!window.localStorage){
  alert("浏览器支持localstorage")
}

export function getToken() {
  console.log('getToken')
  console.log(window.localStorage.getItem('EBANK_TOKEN'))
  console.log('test:'+ window.localStorage.getItem('test'))
  return window.localStorage.getItem('EBANK_TOKEN')
}

export function setToken(token) {
  console.log('setToken')
  window.localStorage.setItem('test', token)
  return window.localStorage.setItem('EBANK_TOKEN', token)
}

export function removeToken() {
  console.log('resetToken')
  return window.localStorage.removeItem('EBANK_TOKEN')
}

