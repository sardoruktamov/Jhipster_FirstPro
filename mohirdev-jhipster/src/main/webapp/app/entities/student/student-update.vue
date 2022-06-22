<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="mohirdevLessonApp.student.home.createOrEditLabel"
          data-cy="StudentCreateUpdateHeading"
          v-text="$t('mohirdevLessonApp.student.home.createOrEditLabel')"
        >
          Create or edit a Student
        </h2>
        <div>
          <div class="form-group" v-if="student.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="student.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('mohirdevLessonApp.student.name')" for="student-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="student-name"
              data-cy="name"
              :class="{ valid: !$v.student.name.$invalid, invalid: $v.student.name.$invalid }"
              v-model="$v.student.name.$model"
              required
            />
            <div v-if="$v.student.name.$anyDirty && $v.student.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.student.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('mohirdevLessonApp.student.lastName')" for="student-lastName">Last Name</label>
            <input
              type="text"
              class="form-control"
              name="lastName"
              id="student-lastName"
              data-cy="lastName"
              :class="{ valid: !$v.student.lastName.$invalid, invalid: $v.student.lastName.$invalid }"
              v-model="$v.student.lastName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('mohirdevLessonApp.student.age')" for="student-age">Age</label>
            <input
              type="number"
              class="form-control"
              name="age"
              id="student-age"
              data-cy="age"
              :class="{ valid: !$v.student.age.$invalid, invalid: $v.student.age.$invalid }"
              v-model.number="$v.student.age.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('mohirdevLessonApp.student.birthOfDay')" for="student-birthOfDay"
              >Birth Of Day</label
            >
            <div class="d-flex">
              <input
                id="student-birthOfDay"
                data-cy="birthOfDay"
                type="datetime-local"
                class="form-control"
                name="birthOfDay"
                :class="{ valid: !$v.student.birthOfDay.$invalid, invalid: $v.student.birthOfDay.$invalid }"
                required
                :value="convertDateTimeFromServer($v.student.birthOfDay.$model)"
                @change="updateInstantField('birthOfDay', $event)"
              />
            </div>
            <div v-if="$v.student.birthOfDay.$anyDirty && $v.student.birthOfDay.$invalid">
              <small class="form-text text-danger" v-if="!$v.student.birthOfDay.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.student.birthOfDay.ZonedDateTimelocal"
                v-text="$t('entity.validation.ZonedDateTimelocal')"
              >
                This field should be a date and time.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('mohirdevLessonApp.student.phoneNumber')" for="student-phoneNumber"
              >Phone Number</label
            >
            <input
              type="text"
              class="form-control"
              name="phoneNumber"
              id="student-phoneNumber"
              data-cy="phoneNumber"
              :class="{ valid: !$v.student.phoneNumber.$invalid, invalid: $v.student.phoneNumber.$invalid }"
              v-model="$v.student.phoneNumber.$model"
              required
            />
            <div v-if="$v.student.phoneNumber.$anyDirty && $v.student.phoneNumber.$invalid">
              <small class="form-text text-danger" v-if="!$v.student.phoneNumber.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('mohirdevLessonApp.student.address')" for="student-phoneNumber"
              >Address</label
            >
            <input
              type="text"
              class="form-control"
              name="address"
              id="student-address"
              data-cy="address"
              :class="{ valid: !$v.student.studentAddress.$invalid, invalid: $v.student.studentAddress.$invalid }"
              v-model="$v.student.studentAddress.$model"
              required
            />
            <div v-if="$v.student.studentAddress.$anyDirty && $v.student.studentAddress.$invalid">
              <small class="form-text text-danger" v-if="!$v.student.address.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.student.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./student-update.component.ts"></script>
