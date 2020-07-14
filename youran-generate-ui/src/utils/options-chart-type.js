const chartTypeOptions = [
  {
    value: 1,
    label: '明细表',
    name: 'detailList',
    icon: 'detail-list',
    class: 'color-warning',
    demoComponent: 'barLineDemo',
    aggregation: false
  },
  {
    value: 2,
    label: '聚合表',
    name: 'aggTable',
    icon: 'agg-table',
    class: 'color-primary',
    demoComponent: 'barLineDemo',
    aggregation: true
  },
  {
    value: 3,
    label: '柱线图',
    name: 'barLine',
    icon: 'chart',
    class: 'color-success',
    demoComponent: 'barLineDemo',
    aggregation: true
  },
  {
    value: 4,
    label: '饼图',
    name: 'pie',
    icon: 'pie-chart',
    class: 'color-danger',
    demoComponent: 'barLineDemo',
    aggregation: true
  }
]

export default {
  /**
   * 图表类型
   */
  chartTypeOptions,
  getChartTypeOption (value) {
    return chartTypeOptions.find(option => option.value === value)
  }
}