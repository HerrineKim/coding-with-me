<template>
	<div
		class="row parti-chat-box"
		style="height: 95vh; font-family: 'Elice Digital Baeum', sans-serif"
	>
		<div class="col-5" style="border: 1px solid #ff5722">
			<div class="column" style="border: 1px solid #ff5722; height: 100%">
				<div class="col-1">
					<q-banner
						dense
						inline-actions
						class="text-white q-pa-md"
						style="
							border: 1px solid #ff5722;
							background-color: #ff5722;
							font-family: 'Elice Digital Baeum', sans-serif;
						"
					>
						{{ piniaCommonExamData.formattedTime }}

						<template v-slot:action>
							<q-icon name="access_time" class="cursor-pointer"> </q-icon>
						</template>
					</q-banner>
				</div>

				<div class="col-1">
					<div
						class="q-pa-sm text-h6"
						style="font-family: 'Elice Digital Baeum', sans-serif"
					>
						{{ piniaCommonExamData.testName }}
					</div>
				</div>
				<div class="q-pa-sm col-5" style="border: 1px solid red">
					<p>오프라인</p>
					<p>온라인</p>
				</div>
				<div class="q-pa-sm col-5" style="border: 1px solid red">
					<p>제출 완료</p>
					<p>제출 미완료</p>
				</div>
			</div>
		</div>

		<div
			class="col-7"
			style="
				font-family: 'Elice Digital Baeum', sans-serif;
				border: 1px solid red;
			"
		>
			<div
				class="column justify-center"
				style="height: 95vh; font-family: 'OTWelcomeBA'"
			>
				<q-scroll-area class="col-10 q-pa-xl" style="width: 100%">
					<span
						v-for="(chat, index) in teacherVideo.state.chatting"
						:key="index"
					>
						<q-chat-message
							v-if="chat.sender === teacherVideo.state.myUserName"
							:name="chat.sender"
							:text="[chat.message]"
							stamp="just second"
							sent
							bg-color="amber-7"
							style="font-family: 'Elice Digital Baeum', sans-serif"
						/>
						<q-chat-message
							v-if="chat.sender !== teacherVideo.state.myUserName"
							:name="chat.sender"
							style="font-family: 'Elice Digital Baeum', sans-serif"
							avatar="https://cdn.quasar.dev/img/avatar3.jpg"
							:text="[chat.message]"
							stamp="just second"
							bg-color="amber-7"
						/>
					</span>
				</q-scroll-area>

				<div class="q-px-md justify-center col-2">
					<q-input
						bottom-slots
						v-model="myChatInput"
						label="메시지를 입력하세요"
						:dense="true"
						color="teal"
						style="font-family: 'Elice Digital Baeum', sans-serif"
						@keydown.enter.prevent="
							teacherVideo.sendMessage(myChatInput);
							myChatInput = '';
						"
					>
						<!-- <template v-slot:before>
							<q-avatar>
								<img src="https://cdn.quasar.dev/img/avatar5.jpg" />
							</q-avatar>
						</template> -->

						<template v-slot:append>
							<q-icon
								v-if="myChatInput !== ''"
								name="close"
								@click="myChatInput = ''"
								class="cursor-pointer"
							/>
						</template>

						<template v-slot:after>
							<q-btn
								round
								dense
								flat
								icon="send"
								@click="
									teacherVideo.sendMessage(myChatInput);
									myChatInput = '';
								"
							/>
						</template>
					</q-input>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { ref, onMounted, onUnmounted } from 'vue';
import { commonExamData } from 'src/stores/ExamProgress/common.js';
import { teacherVideoStore } from 'src/stores/teacherVideo.store.js';
export default {
	components: {},

	props: {},
	setup() {
		const myChatInput = ref('');
		const piniaCommonExamData = commonExamData();
		const teacherVideo = teacherVideoStore();

		let timer;
		onMounted(() => {
			timer = setInterval(() => {
				if (piniaCommonExamData.timeLimit > 0)
					piniaCommonExamData.timeLimit -= 1;
			}, 1000);
		});
		onUnmounted(() => {
			if (timer) clearInterval(timer);
		});

		return {
			myChatInput,
			piniaCommonExamData,
			teacherVideo: teacherVideo,
		};
	},
};
</script>

<style lang="scss" scoped></style>