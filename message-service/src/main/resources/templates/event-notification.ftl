<#include "header.ftl">

			<div class="content" style="font-family: &quot;Times New Roman&quot;, Times, serif;background: rgb(226, 226, 225);border: 1px solid rgb(221, 221, 221);padding: 9px;font-size: 14px;line-height: 18px;color: rgb(17, 17, 17);word-wrap: break-word;">
				<b><span>Hello,</span></b>
				<p>Please find the meeting details with Google Calendar invitation below.</p>

				<p>
					<b>Topic: </b> ${contentDetails.topic}
				</p>
				<p>
					<b>Link: </b> ${contentDetails.link}
				</p>
				<p>
					<b>When: </b> ${contentDetails.slotDate} ${contentDetails.startTime} to ${contentDetails.endTime}
				</p>
				<p>
					<b>Attendees: </b> <ul><li>${contentDetails.hostName}</li><li>${contentDetails.participantName}</li></ul>
				</p>
			</div>

<#include "footer.ftl">