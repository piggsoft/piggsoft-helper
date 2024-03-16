# piggsoft-helper

一个工具集合

## api-version

在`@Controller`中加入`@ApiVersion`即可实现同一个Api的不同版本访问

```java
import com.piggsoft.spring.boot.starter.helper.apiversion.annotation.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{version}/home")
public class HomeController {

    @RequestMapping("/search")
    public String search() {
        return "search-version-base";
    }

    @RequestMapping("/search")
    @ApiVersion("2.0")
    public String searchVersion2() {
        return "search-2.0";
    }
}
```
调用时
```shell
#version1
request GET /api/1.0/home/search
respinse search-version-base

#version2
request GET /api/1.0/home/search
respinse search-2.0

#version3
request GET /api/1.0/home/search
respinse search-version-base

```