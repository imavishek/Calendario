<#include "header.ftl">

			<div class="content" style="font-family: &quot;Times New Roman&quot;, Times, serif;background: rgb(226, 226, 225);border: 1px solid rgb(221, 221, 221);padding: 9px;font-size: 14px;line-height: 18px;color: rgb(17, 17, 17);word-wrap: break-word;">
				<b><span>Hi ${contentDetails.recipientName},</span></b>
				<p>Welcome to Calendario. Your emailId has been successfully registered. Please <a href="${contentDetails.url}">Click Here<a/> to active your account.</p>
				
				<p>
					If you're having trouble clicking the above link then copy and paste the below URL into your web browser:<br><br>
					<a href="${contentDetails.url}">${contentDetails.url}<a/>
				</p>
				<p>This link is  valid for the next 24hrs.</p>
			</div>

<#include "footer.ftl">