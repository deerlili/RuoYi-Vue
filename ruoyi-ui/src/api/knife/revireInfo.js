import request from '@/utils/request'

// 查询追溯号管理列表
export function listRevireInfo(query) {
  return request({
    url: '/knife/revireInfo/list',
    method: 'get',
    params: query
  })
}

// 查询追溯号管理详细
export function getRevireInfo(reviewId) {
  return request({
    url: '/knife/revireInfo/' + reviewId,
    method: 'get'
  })
}

// 新增追溯号管理
export function addRevireInfo(data) {
  return request({
    url: '/knife/revireInfo',
    method: 'post',
    data: data
  })
}

// 修改追溯号管理
export function updateRevireInfo(data) {
  return request({
    url: '/knife/revireInfo',
    method: 'put',
    data: data
  })
}

// 删除追溯号管理
export function delRevireInfo(reviewId) {
  return request({
    url: '/knife/revireInfo/' + reviewId,
    method: 'delete'
  })
}
