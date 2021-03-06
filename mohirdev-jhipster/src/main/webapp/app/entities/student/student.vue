<template>
  <div>
    <h2 id="page-heading" data-cy="StudentHeading">
      <span v-text="$t('mohirdevLessonApp.student.home.title')" id="student-heading">Students</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('mohirdevLessonApp.student.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'StudentCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-student"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('mohirdevLessonApp.student.home.createLabel')"> Create a new Student </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && students && students.length === 0">
      <span v-text="$t('mohirdevLessonApp.student.home.notFound')">No students found</span>
    </div>
    <div class="table-responsive" v-if="students && students.length > 0">
      <table class="table table-striped" aria-describedby="students">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span v-text="$t('mohirdevLessonApp.student.name')">Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lastName')">
              <span v-text="$t('mohirdevLessonApp.student.lastName')">Last Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lastName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('age')">
              <span v-text="$t('mohirdevLessonApp.student.age')">Age</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'age'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('birthOfDay')">
              <span v-text="$t('mohirdevLessonApp.student.birthOfDay')">Birth Of Day</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'birthOfDay'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('phoneNumber')">
              <span v-text="$t('mohirdevLessonApp.student.phoneNumber')">Phone Number</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneNumber'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('address')">
              <span v-text="$t('mohirdevLessonApp.student.address')">Address</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="student in students" :key="student.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'StudentView', params: { studentId: student.id } }">{{ student.id }}</router-link>
            </td>
            <td>{{ student.name }}</td>
            <td>{{ student.lastName }}</td>
            <td>{{ student.age }}</td>
            <td>{{ student.birthOfDay ? $d(Date.parse(student.birthOfDay), 'short') : '' }}</td>
            <td>{{ student.phoneNumber }}</td>
            <td>{{ student.studentAddress }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'StudentView', params: { studentId: student.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'StudentEdit', params: { studentId: student.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(student)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="mohirdevLessonApp.student.delete.question" data-cy="studentDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-student-heading" v-text="$t('mohirdevLessonApp.student.delete.question', { id: removeId })">
          Are you sure you want to delete this Student?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-student"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeStudent()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="students && students.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./student.component.ts"></script>
