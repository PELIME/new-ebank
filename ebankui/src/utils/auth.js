const TokenKey = 'EBANK_TOKEN'
const Cookies=window.localStorage

export function getToken() {
  return Cookies.getItem(TokenKey)
}

export function setToken(token) {
  return Cookies.setItem(TokenKey, token)
}

export function removeToken() {
  return Cookies.removeItem(TokenKey)
}

