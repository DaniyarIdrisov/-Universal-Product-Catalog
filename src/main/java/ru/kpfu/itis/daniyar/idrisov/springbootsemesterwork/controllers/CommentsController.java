package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CommentDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.details.UserDetailsImpl;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.CommentsService;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.ItemsService;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.UsersService;

import javax.annotation.security.PermitAll;
import java.util.List;

@Controller
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/showComments/{item_id}")
    public String showComments(Model model, @PathVariable("item_id") Long itemId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = usersService.getUsernamePrincipal(userDetails);
        ItemDto itemDto = itemsService.getItemById(itemId);
        List<CommentDto> commentDtoList = commentsService.getCommentsByItemId(itemId);
        UserDto userDto = usersService.getUserByLogin(username);
        model.addAttribute("item", itemDto);
        model.addAttribute("comments", commentDtoList);
        model.addAttribute("user", userDto);
        return "show_comments";
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/enterComment/{item_id}")
    public String enterComment(@PathVariable("item_id") Long itemId, @AuthenticationPrincipal UserDetailsImpl userDetails, CommentDto commentDto) {
        String username = usersService.getUsernamePrincipal(userDetails);
        commentsService.addComment(commentDto, itemId, username);
        return "redirect:/showComments/{item_id}";
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/deleteComment/{item_id}/{comment_id}")
    public String deleteComment(@PathVariable("comment_id") Long commentId, @PathVariable("item_id") Long item_id) {
        commentsService.deleteComment(commentId);
        return "redirect:/showComments/{item_id}";
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/editCommentPage/{item_id}/{comment_id}")
    public String editCommentPage(@PathVariable("comment_id") Long commentId, Model model, @PathVariable("item_id") Long item_id) {
        CommentDto commentDto = commentsService.getCommentById(commentId);
        ItemDto itemDto = itemsService.getItemById(item_id);
        model.addAttribute("comment", commentDto);
        model.addAttribute("item", itemDto);
        return "edit_comments";
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/editComment/{item_id}/{comment_id}")
    public String editComment(@PathVariable("comment_id") Long commentId, CommentDto commentDto, @PathVariable("item_id") Long item_id) {
        System.out.println(commentDto.getText());
        if (commentDto.getText().equals("")) {
            return "redirect:/editCommentPage/{item_id}/{comment_id}";
        }
        commentsService.updateComment(commentId, commentDto);
        return "redirect:/editCommentPage/{item_id}/{comment_id}";
    }
}
