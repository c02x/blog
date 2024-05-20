<script>
export default {
  name: 'MenuItem',
  functional: true,//这个组件根据传入的icon和title属性动态渲染一个包含图标和标题的菜单项。
//函数式组件的设计使得这个组件在渲染过程中更加轻量和高效。
  props: {
    icon: {
      type: String,
      default: ''
    },
    title: {
      type: String,
      default: ''
    }
  },
  render(h, context) {
    const { icon, title } = context.props
    const vnodes = []

    if (icon) {
      vnodes.push(<svg-icon icon-class={icon}/>)
    }

    if (title) {
      vnodes.push(<span slot='title'>{(title)}</span>)
    }
    return vnodes
  }
}
</script>
