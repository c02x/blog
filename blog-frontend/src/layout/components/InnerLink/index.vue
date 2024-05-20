
<script>
export default { // 根据当前路由的 meta 中的 link 属性加载一个外部网页，并将其显示在一个 iframe 中
  data() {
    return {};
  },
  render() {
    const { $route: { meta: { link } }, } = this;
    if ({ link }.link === "") {
      return "404";
    }
    let url = { link }.link;
    const height = document.documentElement.clientHeight - 94.5 + "px";
    const style = { height: height };

    return (
      <div style={style}>
        <iframe
          src={url}
          frameborder="no"
          style="width: 100%; height: 100%"
          scrolling="auto"
        ></iframe>
      </div>
    );
  },
};
</script>
