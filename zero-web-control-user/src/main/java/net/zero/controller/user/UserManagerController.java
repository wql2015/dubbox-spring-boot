package net.zero.controller.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.zero.facade.user.entity.User;

@RequestMapping("/users")
@RestController
public class UserManagerController {
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

	@ApiOperation(value="获取用户列表")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> getUserList() {
		// 处理"/users/"的GET请求，用来获取用户列表
		// 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
		List<User> r = new ArrayList<User>(users.values());
		return r;
	}

	/**
	 * 使用表单的形式请求参数
	 * @param user
	 * @return
	 */
	@ApiOperation(value="创建用户根据表单", notes="根据User对象通过表单请求创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value = "/postUserByForm", method = RequestMethod.POST)
	public String postUserByForm(@ModelAttribute User user) {
		// 处理"/users/"的POST请求，用来创建User
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		users.put(user.getId(), user);
		return "success";
	}
	
	/**
	 * 使用json数据请求
	 * @param user
	 * @return
	 */
	@ApiOperation(value="创建用户根据表单", notes="根据User对象通过json数据创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value = "/postUserByJson", method = RequestMethod.POST)
	public String postUserByJson(@RequestBody User user) {
		// 处理"/users/"的POST请求，用来创建User
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		users.put(user.getId(), user);
		return "success";
	}

	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		// 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
		// url中的id可通过@PathVariable绑定到函数的参数中
		return users.get(id);
	}

	@ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @ModelAttribute User user) {
		// 处理"/users/{id}"的PUT请求，用来更新User信息
		User u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		return "success";
	}

	@ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		// 处理"/users/{id}"的DELETE请求，用来删除User
		users.remove(id);
		return "success";
	}
}
