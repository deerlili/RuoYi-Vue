import request from '@/utils/request'

// 查询追溯号管理列表
export function listInfo(query) {
  return request({
    url: '/knife/info/list',
    method: 'get',
    params: query
  })
}

// 查询追溯号管理详细
export function getInfo(reviewId) {
  return request({
    url: '/knife/info/' + reviewId,
    method: 'get'
  })
}

// 新增追溯号管理
export function addInfo(data) {
  return request({
    url: '/knife/info',
    method: 'post',
    data: data
  })
}

// 修改追溯号管理
export function updateInfo(data) {
  return request({
    url: '/knife/info',
    method: 'put',
    data: data
  })
}

// 删除追溯号管理
export function delInfo(reviewId) {
  return request({
    url: '/knife/info/' + reviewId,
    method: 'delete'
  })
}
