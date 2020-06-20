const TokenKey = 'ebank_token'
if(!window.localStorage){
  alert("浏览器支持localstorage");
}
const Cookies=window.localStorage
export function getToken() {
  console.log('getToken')
  console.log(Cookies.getItem(TokenKey))
  return Cookies.getItem(TokenKey)
}

export function setToken(token) {
  console.log('setToken')
  return Cookies.setItem(TokenKey, token)
}

export function removeToken() {
  return Cookies.removeItem(TokenKey)
}
